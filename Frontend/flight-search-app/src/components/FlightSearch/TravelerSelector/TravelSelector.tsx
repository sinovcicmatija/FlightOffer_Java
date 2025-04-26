import { useState } from 'react';
import './TravelerSelector.css';
import 'bootstrap/dist/css/bootstrap.min.css';

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
    <div className="input-wrapper traveler-wrapper">
      <label>Klasa i putnici</label>
      <input
        type="text"
        readOnly
        value={travelerSummary}
        className="clickable-field"
        onClick={toggleBox}
      />

      {visible && (
        <div className="traveler-box">
          <label>Klasa kabine</label>
          <select value={cabinClass} onChange={(e) => onCabinClassChange(e.target.value)}>
            {cabinClasses.map((cls) => (
              <option key={cls} value={cls}>{cls}</option>
            ))}
          </select>

          <div className="traveler-group">
            <label>Odrasli</label>
            <button className="btn btn-outline-secondary" onClick={() => onAdultsChange(Math.max(1, adults - 1))}>-</button>
            {adults}
            <button className="btn btn-outline-secondary" onClick={() => onAdultsChange(adults + 1)}>+</button>
          </div>

          <div className="traveler-group">
            <label>Djeca</label>
            <button className="btn btn-outline-secondary" onClick={() => onChildrenChange(Math.max(0, children - 1))}>-</button>
            {children}
            <button className="btn btn-outline-secondary" onClick={() => onChildrenChange(children + 1)}>+</button>
          </div>

          <button className="btn btn-primary" onClick={toggleBox}>Primijeni</button>
        </div>
      )}
    </div>
  );
}

export default TravelerSelector;
