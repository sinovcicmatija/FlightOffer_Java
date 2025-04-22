// src/components/FlightSearch/TravelerSelector.tsx
import { useState } from 'react';
import './TravelerSelector.css';

interface Props {
  adults: number;
  children: number;
  cabinClass: string;
  onAdultsChange: (value: number) => void;
  onChildrenChange: (value: number) => void;
  onCabinClassChange: (value: string) => void;
}

const cabinClasses = ['ECONOMY', 'PREMIUM_ECONOMY', 'BUSINESS', 'FIRST'];

function TravelerSelector({
  adults,
  children,
  cabinClass,
  onAdultsChange,
  onChildrenChange,
  onCabinClassChange,
}: Props) {
  const [visible, setVisible] = useState(false);

  const toggleBox = () => setVisible(!visible);
  const travelerSummary = `${adults + children} ${adults + children === 1 ? 'Traveler' : 'Putnika'}, ${cabinClass}`;

  return (
    <div className="traveler-wrapper">
      <input
        type="text"
        readOnly
        value={travelerSummary}
        className="clickable-field"
        onClick={toggleBox}
      />

      {visible && (
        <div className="traveler-box">
          <label>Cabin class</label>
          <select value={cabinClass} onChange={(e) => onCabinClassChange(e.target.value)}>
            {cabinClasses.map((cls) => (
              <option key={cls} value={cls}>{cls}</option>
            ))}
          </select>

          <div className="traveler-group">
            <label>Odrasli</label>
            <button onClick={() => onAdultsChange(Math.max(1, adults - 1))}>-</button>
            {adults}
            <button onClick={() => onAdultsChange(adults + 1)}>+</button>
          </div>

          <div className="traveler-group">
            <label>Djeca</label>
            <button onClick={() => onChildrenChange(Math.max(0, children - 1))}>-</button>
            {children}
            <button onClick={() => onChildrenChange(children + 1)}>+</button>
          </div>

          <button onClick={toggleBox}>Primijeni</button>
        </div>
      )}
    </div>
  );
}

export default TravelerSelector;
