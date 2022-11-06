import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';

import { ErrorDialogComponent } from '../../../shared/components/error-dialog/error-dialog.component';
import { Client } from '../../model/client';
import { ClientsService } from '../../services/clients.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.scss'],
})
export class ClientsComponent implements OnInit {
  clients$: Observable<Client[]> | null = null;

  constructor(
    private clientsService: ClientsService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.refresh();
  }

  refresh() {
    this.clients$ = this.clientsService.list().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar clientes.');
        return of([]);
      })
    );
  }

  onError(erroMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: erroMsg,
    });
  }

  ngOnInit(): void {}

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onEdit(client: Client) {
    this.router.navigate(['edit', client._id], { relativeTo: this.route });
  }

  onRemove(client: Client) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja remover esse cliente?',
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.clientsService.remove(client._id).subscribe(
          () => {
            this.refresh();
            this.snackBar.open('Cliente removido com sucesso!', 'X', {
              duration: 5000,
              verticalPosition: 'top',
              horizontalPosition: 'center',
            });
          },
          () => this.onError('Erro ao tentar remover cliente.')
        );
      }
    });
  }
}
