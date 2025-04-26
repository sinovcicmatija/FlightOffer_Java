import { FlightOfferData } from '../../models/FlightOfferData';
import { currencySymbols } from '../../models/CurrencySimbols';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState } from 'react';
import './FlightResults.css'

interface Props {
  offers: FlightOfferData[];
}

function FlightResults({ offers }: Props) {
  const [currentPage, setCurrentPage] = useState(0);
  const [itemsPerPage, setItemsPerPage] = useState(10);

  if (!offers || offers.length === 0) {
    return (
      <div className="alert alert-warning mt-4" role="alert">
        Nema dostupnih letova za odabrane parametre.
      </div>
    );
  }

  const sortedOffers = [...offers].sort((a, b) => {
    return parseDate(a.departureDate).getTime() - parseDate(b.departureDate).getTime();
  });
  const offset = currentPage * itemsPerPage;
  const currentOffers = sortedOffers.slice(offset, offset + itemsPerPage);

  const pageCount = Math.ceil(offers.length / itemsPerPage);

  function parseDate(dateStr: string): Date {
    const regex = /(\d{2})\.(\d{2})\.(\d{4})\. u (\d{2}):(\d{2})/;
    const match = dateStr.match(regex);
  
    if (!match) return new Date(); 
  
    const [, day, month, year, hours, minutes] = match;
    return new Date(Number(year), Number(month) - 1, Number(day), Number(hours), Number(minutes));
  }

  return (
    <>
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
          {currentOffers.map((offer, index) => (
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
                {offer.numberOfTransfersReturn != null && offer.numberOfTransfersReturn > 0 && (
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

      <div className="d-flex justify-content-between align-items-center mt-3">
        <div>
          <label htmlFor="itemsPerPageSelect" className="form-label me-2 ps-2">Rezultata po stranici:</label>
          <select
            id="itemsPerPageSelect"
            className="form-select d-inline-block w-auto"
            value={itemsPerPage}
            onChange={(e) => {
              setItemsPerPage(Number(e.target.value));
              setCurrentPage(0);
            }}
          >
            <option value={5}>5</option>
            <option value={10}>10</option>
            <option value={25}>25</option>
            <option value={50}>50</option>
          </select>
        </div>

        <ul className="pagination mb-0 pb-2 pe-2">
          <li className={`page-item ${currentPage === 0 ? 'disabled' : ''}`}>
            <button className="page-link" onClick={() => setCurrentPage(currentPage - 1)}>Prethodna</button>
          </li>

          {Array.from({ length: pageCount }, (_, i) => {
            if (
              i === 0 ||
              i === pageCount - 1 ||
              (i >= currentPage - 1 && i <= currentPage + 1)
            ) {
              return (
                <li key={i} className={`page-item ${currentPage === i ? 'active' : ''}`}>
                  <button className="page-link" onClick={() => setCurrentPage(i)}>
                    {i + 1}
                  </button>
                </li>
              );
            } else if (
              i === currentPage - 2 ||
              i === currentPage + 2
            ) {
              return (
                <li key={i} className="page-item disabled">
                  <span className="page-link">...</span>
                </li>
              );
            } else {
              return null;
            }
          })}


          <li className={`page-item ${currentPage === pageCount - 1 ? 'disabled' : ''}`}>
            <button className="page-link" onClick={() => setCurrentPage(currentPage + 1)}>Sljedeća</button>
          </li>
        </ul>
      </div>
    </>
  );
}

export default FlightResults;
