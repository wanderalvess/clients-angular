import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { latLng, MapOptions, marker, tileLayer } from 'leaflet';


import { Client } from '../../model/client';


@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.scss']

})
export class ClientsListComponent implements OnInit  {

  @Input() clients: Client[] = []
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() remove = new EventEmitter(false);

  displayedColumns = [ '_id','name','document','address','latitude','longitude', 'actions' ]

  constructor() { }

  ngOnInit(): void {
  }

  onAdd() {
    this.add.emit(true);
  }

  onEdit(client: Client) {
    this.edit.emit(client)
  }

  onDelete(client: Client) {
    this.remove.emit(client);
  }

}
