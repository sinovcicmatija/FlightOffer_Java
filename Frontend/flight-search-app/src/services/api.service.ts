import axios from "axios";
import { config } from "./config";
import { AirportData } from "../models/AirportData";

const apiUrl = config.apiUrl;

export const getAirportData = async (keyword: string): Promise<AirportData[]> => {
    const { data } = await axios.get(`${apiUrl}/Airport/airports?keyword=${keyword}`);
    return data;
  };