import { useState } from 'react';
import { AirportData } from '../../models/AirportData';
import AirportAutocomplete from './AirportAutocomplete';
import './FlightSearch.css';
import DateSelector from './DateSelector';
import TravelerSelector from './TravelSelector';
import CurrencySelector from './CurrencySelector';
import { FlightSearchDTO } from '../../models/FlightSearchDTO';
import { getFlightOfferData } from '../../services/api.service';

function FlightSearch() {
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

    const handleSearchClick = () => {
        if (!selectedDepartureIata || !selectedArrivalIata || !departureDate) {
            alert('Molimo ispunite sve obavezne podatke.');
            return;
        }

        const dto: FlightSearchDTO = {
            originIata: selectedDepartureIata,
            destinationIata: selectedArrivalIata,
            departureDate: departureDate.toISOString().split('T')[0],
            returnDate: isRoundTrip && arrivalDate ? arrivalDate.toISOString().split('T')[0] : undefined,
            isRoundTrip,
            adults,
            children,
            cabinClass,
            currency
        };

        getFlightOfferData(dto)
            .then((offers) => console.log("Rezultati:", offers)) 
            .catch((err) => console.error("Greška:", err));
    };

    return (
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

                <button className="search-btn" onClick={handleSearchClick}>
                    Pretraži
                </button>
            </div>
        </div>
    );
}

export default FlightSearch;
