import { ErrorDialogComponent } from './../../shared/components/error-dialog/error-dialog.component';
import { ClientsService } from './../services/clients.service';
import { Component, OnInit } from '@angular/core';
import { Client } from '../model/client';
import { catchError, Observable, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.scss']
})
export class ClientsComponent implements OnInit {

  clients$: Observable<Client[]>;
  displayedColumns = [ 'code','name','document','latitude','longitude' ]

  constructor(
    private clientsService: ClientsService,
    public dialog: MatDialog
    ) {
    this. clients$ = this.clientsService.list()
    .pipe(
      catchError(error => {
        this.onError('Erro ao carregar Clientes');
        return of([])
      })
    );
  }

  onError(erroMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: erroMsg
    });
  }

  ngOnInit(): void {
  }

}
