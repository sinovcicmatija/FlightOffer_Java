import axios from "axios";
import { config } from "./config";
import { AirportData } from "../models/AirportData";
import { FlightSearchDTO } from "../models/FlightSearchDTO";
import { FlightOfferData } from "../models/FlightOfferData";

const apiUrl = config.apiUrl;

export const getAirportData = async (keyword: string): Promise<AirportData[]> => {
    const { data } = await axios.get(`${apiUrl}/airports?keyword=${keyword}`);
    return data;
  };

export const getFlightOfferData = async (searchData: FlightSearchDTO): Promise<FlightOfferData[]> => {
    const { data } = await axios.post(`${apiUrl}/flight-offers`, searchData);
    console.log(searchData);
    return data;
}