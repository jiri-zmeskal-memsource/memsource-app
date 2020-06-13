import { Component, OnInit } from '@angular/core';
import { Credentials } from '../models/credentials';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'projects-page',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.less']
})
export class ProjectsComponent implements OnInit {

  credentials: Credentials;

  constructor(private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.data.subscribe((data: { credentials: Credentials, projects: any }) => {
      this.credentials = data.credentials;
    });
  }

  isSignedIn(): boolean {
    return !!this.credentials.username;
  }

  open(content) {
  }
}
