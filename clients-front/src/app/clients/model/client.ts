import { LocationChangeEvent } from "@angular/common";
import {Phone} from "./phone";

export interface Client {
  _id: string;
  name: string;
  document: string;
  address: string;
  neighborhood: string;
  latitude: string;
  longitude: string;
  phones: Phone[];
}
