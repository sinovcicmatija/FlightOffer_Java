import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

interface DateSelectorProps {
  label: string;
  date: Date | null;
  onChange: (date: Date | null) => void;
}

function DateSelector({ label, date, onChange }: DateSelectorProps) {
  return (
    <div className="input-wrapper">
      <label>{label}</label>
      <DatePicker
        selected={date}
        onChange={onChange}
        placeholderText="Odaberi datum"
        dateFormat="yyyy-MM-dd"
        className="datepicker-input"
        minDate={new Date()}
      />
    </div>
  );
}

export default DateSelector;
