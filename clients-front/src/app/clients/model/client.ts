import { LocationChangeEvent } from "@angular/common";

export interface Client {
  _id: string;
  code: string;
  name: string;
  document: string;
  latitude: string;
  longitude: string;
}
