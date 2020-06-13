import { Component, OnInit } from '@angular/core';
import { Credentials } from '../models/credentials';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { BackendService } from '../services/backend.service';
import { Project } from '../models/project';

@Component({
  selector: 'projects-page',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.less']
})
export class ProjectsComponent implements OnInit {

  cannotSignInErrorVisible: boolean;

  projects: Project[];
  credentials: Credentials;
  newCredentials: Credentials;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private modalService: NgbModal,
              private backendService: BackendService) {
  }

  ngOnInit(): void {
    this.route.data.subscribe((data: { credentials: Credentials, projects: Project[] }) => {
      this.credentials = data.credentials;
      this.newCredentials = { username: this.credentials.username } as Credentials;
      this.projects = data.projects;
      this.cannotSignInErrorVisible = false;
    });
  }

  isSignedIn(): boolean {
    return !!this.credentials.username;
  }

  signInAs() {
    this.backendService.updateCredentials(this.newCredentials).subscribe(res => {
      this.router.navigate(['projects']);
      this.modalService.dismissAll();
    }, err => {
      this.cannotSignInErrorVisible = true;
    });
  }

  signOut() {
    this.backendService.removeCredentials().subscribe(res => {
      this.router.navigate(['projects']);
    });
  }

  open(content) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then(
      result => {
      },
      reason => {
      }
    );
  }
}
