import { FlightOfferData } from '../../models/FlightOfferData';
import { currencySymbols } from '../../models/CurrencySimbols';
import './FlightResults.css'

interface Props {
  offers: FlightOfferData[];
}

function FlightResults({ offers }: Props) {
  return (
    <table className="results-table">
      <thead>
        <tr>
          <th>Polazak</th>
          <th>Odredište</th>
          <th>Datumi</th>
          <th>Presjedanja</th>
          <th>Putnici</th>
          <th>Cijena</th>
        </tr>
      </thead>
      <tbody>
        {offers.map((offer, index) => (
          <tr key={index}>
            <td>{offer.departureAirport}</td>
            <td>{offer.arrivalAirport}</td>
            <td>
              <div><strong>Polazak:</strong> {offer.departureDate}</div>
              {offer.returnDate != null && (
                <div><strong>Povratak:</strong> {offer.returnDate}</div>
              )}
            </td>
            <td>
              <div><strong>Odlazak:</strong> {offer.numberOfTransfersDeparture}</div>
              {offer.numberOfTransfersReturn !== undefined && (
                <div><strong>Povratak:</strong> {offer.numberOfTransfersReturn}</div>
              )}
            </td>
            <td>{offer.passengerCount}</td>
            <td>
              {offer.totalPrice}
              {currencySymbols[offer.currency] || offer.currency}
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default FlightResults;
