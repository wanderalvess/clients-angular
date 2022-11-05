import { LocationChangeEvent } from "@angular/common";

export interface Client {
  _id: string;
  name: string;
  document: string;
  address: string;
  latitude: string;
  longitude: string;
}
