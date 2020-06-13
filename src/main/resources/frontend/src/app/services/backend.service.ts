import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Credentials } from '../models/credentials';

@Injectable()
export class BackendService {

  constructor(private httpClient: HttpClient) {
  }

  loadCredentials(): Observable<Credentials> {
    return this.httpClient.get<Credentials>('/api/credentials');
  }

  updateCredentials(credentials: Credentials): Observable<any> {
    return this.httpClient.put('/api/credentials',
      { username: credentials.username, password: credentials.password }
    );
  }
}
