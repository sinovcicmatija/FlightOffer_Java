import { useEffect, useState } from "react";
import { getAirportData } from "../../services/api.service";
import { AirportData } from "../../models/AirportData";

interface Props {
  label: string;
  value: string;
  onChange: (value: string) => void;
  onSelect: (airport: AirportData) => void;
}

function AirportAutocomplete({ label, value, onChange, onSelect }: Props) {
  const [suggestions, setSuggestions] = useState<AirportData[]>([]);

  useEffect(() => {
    const timeout = setTimeout(() => {
      if (value.trim().length > 0) {
        getAirportData(value)
          .then((data) => setSuggestions(data))
          .catch((err) => console.error("Greška kod dohvaćanja aerodroma:", err));
      } else {
        setSuggestions([]);
      }
    }, 500);

    return () => clearTimeout(timeout);
  }, [value]);

  const handleSelect = (airport: AirportData) => {
    onSelect(airport);
    onChange(`${airport.cityName}, ${airport.airportName} (${airport.iataCode})`);
    setSuggestions([]);
  };

  return (
    <div className="input-wrapper">
      <label>{label}</label>
      <input type="text" value={value} onChange={(e) => onChange(e.target.value)} />
      {suggestions.length > 0 && (
        <ul className="suggestions-list">
          {suggestions.map((airport) => (
            <li key={`${airport.iataCode}-${airport.airportName}`} onClick={() => handleSelect(airport)}>
              {airport.cityName}, {airport.airportName} ({airport.iataCode})
            </li>
          ))}
        </ul>
      )}
      </div>
  );
}

export default AirportAutocomplete;
