import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first } from 'rxjs';

import { Client } from '../model/client';

@Injectable({
  providedIn: 'root',
})
export class ClientsService {
  private readonly API = 'api/clients';

  constructor(private httpClient: HttpClient) {}


  list() {
    return this.httpClient.get<Client[]>(this.API).pipe(
      first(),
      delay(1000)
    );
  }

  loadById(id: string) {
    return this.httpClient.get<Client>(`${this.API}/${id}`);
  }

  private create(record: Partial<Client>) {
    return this.httpClient.post<Client>(this.API, record).pipe(first());
  }

  save(record: Partial<Client>) {
    if (record._id) {
      return this.update(record);
    }
    return this.create(record);
  }

  private update(record: Partial<Client>) {
    return this.httpClient
      .put<Client>(`${this.API}/${record._id}`, record)
      .pipe(first());
  }

  remove(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }
}
