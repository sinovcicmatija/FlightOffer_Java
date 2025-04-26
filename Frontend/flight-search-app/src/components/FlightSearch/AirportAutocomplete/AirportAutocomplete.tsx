import { useEffect, useState } from "react";
import { getAirportData } from "../../../services/api.service";
import { AirportData } from "../../../models/AirportData";
import './AirportAutocomplete.css';

interface Props {
  label: string;
  value: string;
  onChange: (value: string) => void;
  onSelect: (airport: AirportData) => void;
}

function AirportAutocomplete({ label, value, onChange, onSelect }: Props) {
  const [suggestions, setSuggestions] = useState<AirportData[]>([]);
  const [hasSearched, setHasSearched] = useState(false);

  useEffect(() => {
    const timeout = setTimeout(() => {
      if (value.trim().length > 0) {
        getAirportData(value)
          .then((data) => {
            setSuggestions(data);
            setHasSearched(true);
          })
          .catch((err) => console.error("Greška kod dohvaćanja aerodroma:", err));
      } else {
        setSuggestions([]);
        setHasSearched(false);
      }
    }, 500);

    return () => clearTimeout(timeout);
  }, [value]);

  const handleSelect = (airport: AirportData) => {
    onSelect(airport);
    setSuggestions([]);
    setHasSearched(false)
  };

  return (
    <div className="input-wrapper">
      <label>{label}</label>
      <input type="text" value={value} onChange={(e) => onChange(e.target.value)} />
      {(suggestions.length > 0 || hasSearched) && (
        <ul className="suggestions-list">
          {suggestions.length > 0 ? (
            suggestions.map((airport) => (
              <li key={`${airport.iataCode}-${airport.airportName}`} onClick={() => handleSelect(airport)}>
                {airport.cityName}, {airport.airportName} ({airport.iataCode})
              </li>
            ))
          ) : (
            <li className="no-results">Nema rezultata za odabrani pojam.</li>
          )}
        </ul>
      )}
    </div>
  );
}

export default AirportAutocomplete;
