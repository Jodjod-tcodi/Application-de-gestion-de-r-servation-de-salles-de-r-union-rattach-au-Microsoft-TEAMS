import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { RoomComponent } from './components/room/room.component';
import { ReservationComponent} from './components/reservation/reservation.component';
import { AboutComponent } from './components/about/about.component';
import { ProfileComponent } from './pages/profile/profile.component';

// Define the routes for the application
export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'rooms', component: RoomComponent },
  { path: 'reservations', component: ReservationComponent },
  { path: 'about', component: AboutComponent },
  { path: 'profile', component: ProfileComponent },
  // Redirect to login if no path matches
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login' },

];
