import React from 'react';

interface Props {
  currency: string;
  onCurrencyChange: (value: string) => void;
}

const currencies = ['USD', 'EUR', 'HRK'];

function CurrencySelector({ currency, onCurrencyChange }: Props) {
  return (
    <div className="input-wrapper">
      <label>Valuta</label>
      <select value={currency} onChange={(e) => onCurrencyChange(e.target.value)}>
        {currencies.map((curr) => (
          <option key={curr} value={curr}>
            {curr}
          </option>
        ))}
      </select>
    </div>
  );
}

export default CurrencySelector;
