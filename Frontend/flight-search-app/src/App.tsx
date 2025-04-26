import React, { useState } from 'react';
import FlightSearch from './components/FlightSearch/FlightSearch';
import FlightResults from './components/FlightResults/FlightResults';
import { FlightOfferData } from './models/FlightOfferData';

function App() {
  const [offers, setOffers] = useState<FlightOfferData[]>([]);

  return (
    <div className="App">
      <h1>Pretraga letova</h1>
      <FlightSearch onResultsFound={setOffers}/>
      {offers.length > 0 && <FlightResults offers={offers} />}
    </div>
  );
}

export default App;

