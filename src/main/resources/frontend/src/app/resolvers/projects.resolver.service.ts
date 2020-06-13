import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { BackendService } from '../services/backend.service';
import { Project } from '../models/project';
import { catchError, map } from 'rxjs/operators';


@Injectable()
export class ProjectsResolverService implements Resolve<Project[]> {
  constructor(private backendService: BackendService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Project[]> {
    return this.backendService.loadProjects().pipe(map(p => p)).pipe(catchError(err => {
      if (err.status == 401) {
        return of([])
      } else {
        throw err;
      }
    }));
  }
}
