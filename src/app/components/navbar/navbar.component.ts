import { Component } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(public router: Router) {}

  isLoggedIn = true; // Adjust this with AuthService later

  onLoginClick() {
    this.router.navigate(['/login']);
  }

  onLogoutClick() {
    this.isLoggedIn = false;
    this.router.navigate(['/login']);
  }

  onProfileClick(): void {
    this.router.navigate(['/profile']);
  }
}
