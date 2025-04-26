import { useState } from 'react';
import { AirportData } from '../../models/AirportData';
import AirportAutocomplete from './AirportAutocomplete/AirportAutocomplete';
import './FlightSearch.css';
import DateSelector from './DateSelector/DateSelector';
import TravelerSelector from './TravelerSelector/TravelSelector';
import CurrencySelector from './CurrencySelector/CurrencySelector';
import { FlightSearchDTO } from '../../models/FlightSearchDTO';
import { getFlightOfferData } from '../../services/api.service';
import { FlightOfferData } from '../../models/FlightOfferData';
import 'bootstrap/dist/css/bootstrap.min.css';
import { createPortal } from 'react-dom';

interface Props {
    onResultsFound: (results: FlightOfferData[]) => void;
}

const formatDate = (date: Date) => {
    const year = date.getFullYear();
    const month = `${date.getMonth() + 1}`.padStart(2, '0');
    const day = `${date.getDate()}`.padStart(2, '0');
    return `${year}-${month}-${day}`;
};

function FlightSearch({ onResultsFound }: Props) {
    const [departureInput, setDepartureInput] = useState('');
    const [arrivalInput, setArrivalInput] = useState('');
    const [selectedDepartureIata, setSelectedDepartureIata] = useState<string | null>(null);
    const [selectedArrivalIata, setSelectedArrivalIata] = useState<string | null>(null);
    const [departureDate, setDepartureDate] = useState<Date | null>(null);
    const [arrivalDate, setArrivalDate] = useState<Date | null>(null);
    const [adults, setAdults] = useState(1);
    const [children, setChildren] = useState(0);
    const [cabinClass, setCabinClass] = useState('ECONOMY');
    const [currency, setCurrency] = useState('USD');
    const [isRoundTrip, setIsRoundTrip] = useState(false);
    const [showAlert, setShowAlert] = useState(false);
    const [alertMessage, setAlertMessage] = useState('');
    const [isLoading, setIsLoading] = useState(false);

    const handleSearchClick = () => {
        let missingFields = [];

        onResultsFound([]);

        if (!selectedDepartureIata) missingFields.push('Polazni aerodrom');
        if (!selectedArrivalIata) missingFields.push('Odredišni aerodrom');
        if (!departureDate) missingFields.push('Datum polaska');

        if (missingFields.length > 0) {
            setAlertMessage(`Molimo ispunite sljedeća polja: ${missingFields.join(', ')}`);
            setShowAlert(true);
            return;
        }
        setShowAlert(false);
        setAlertMessage('');

        setIsLoading(true);

        const dto: FlightSearchDTO = {
            originIata: selectedDepartureIata!,
            destinationIata: selectedArrivalIata!,
            departureDate: departureDate ? formatDate(departureDate) : '',
            returnDate: isRoundTrip && arrivalDate ? formatDate(arrivalDate) : undefined,
            isRoundTrip,
            adults,
            children,
            cabinClass,
            currency
        };
        console.log(dto);

        getFlightOfferData(dto)
            .then((offers) => {
                console.log(offers);
                onResultsFound(offers);
                setIsLoading(false);
            })
            .catch((err) => {
                console.error("Greška:", err);
                setIsLoading(false);
            });
    };

    return (
        <>
            {showAlert && createPortal(
                <div className="alert alert-warning alert-dismissible fade show floating-alert" role="alert">
                    {alertMessage}
                    <button type="button" className="btn-close" aria-label="Close" onClick={() => setShowAlert(false)}></button>
                </div>,
                document.getElementById('alert-root')!
            )}
            <div className="form-grid">
                <div className="row-1">

                    <AirportAutocomplete
                        label="Polazni aerodrom"
                        value={departureInput}
                        onChange={setDepartureInput}
                        onSelect={(airport: AirportData) => setSelectedDepartureIata(airport.iataCode)}
                    />

                    <AirportAutocomplete
                        label="Odredišni aerodrom"
                        value={arrivalInput}
                        onChange={setArrivalInput}
                        onSelect={(airport: AirportData) => setSelectedArrivalIata(airport.iataCode)}
                    />

                    <DateSelector
                        label="Datum polaska"
                        date={departureDate}
                        onChange={setDepartureDate}
                    />
                    {isRoundTrip && (
                        <DateSelector
                            label="Datum povratka"
                            date={arrivalDate}
                            onChange={setArrivalDate}
                        />
                    )}

                    <TravelerSelector
                        adults={adults}
                        children={children}
                        cabinClass={cabinClass}
                        onAdultsChange={setAdults}
                        onChildrenChange={setChildren}
                        onCabinClassChange={setCabinClass}
                    />

                    <CurrencySelector currency={currency} onCurrencyChange={setCurrency} />
                </div>
                <div className="row-2">
                    <label className="checkbox-label">
                        <input
                            type="checkbox"
                            checked={isRoundTrip}
                            onChange={(e) => {
                                setIsRoundTrip(e.target.checked);
                                if (!e.target.checked) setArrivalDate(null);
                            }}
                        />
                        Povratno putovanje
                    </label>

                    <button className="btn btn-primary" onClick={handleSearchClick} disabled={isLoading}>
                        {isLoading ? (
                            <>
                                <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                                Učitavanje...
                            </>
                        ) : (
                            "Pretraži"
                        )}
                    </button>
                </div>
            </div>
        </>
    );
}

export default FlightSearch;
