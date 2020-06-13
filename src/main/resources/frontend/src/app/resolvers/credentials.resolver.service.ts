import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Credentials } from '../models/credentials';
import { BackendService } from '../services/backend.service';
import { catchError } from 'rxjs/operators';


@Injectable()
export class CredentialsResolverService implements Resolve<Credentials> {
  constructor(private backendService: BackendService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Credentials> {
    return this.backendService.loadCredentials()
      .pipe(catchError(err => {
        return of({} as Credentials);
      }));
  }
}
