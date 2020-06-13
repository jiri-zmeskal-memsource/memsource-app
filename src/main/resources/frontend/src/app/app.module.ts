import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { BackendService } from './services/backend.service';
import { CredentialsResolverService } from './resolvers/credentials.resolver.service';
import { ProjectsComponent } from './components/projects.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


const routes: Routes = [
  {
    path: 'projects',
    component: ProjectsComponent,
    runGuardsAndResolvers: 'always',
    resolve: {
      credentials: CredentialsResolverService
    }
  }
];

@NgModule({
  declarations: [
    AppComponent,
    ProjectsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })
  ],
  providers: [
    BackendService,
    CredentialsResolverService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
