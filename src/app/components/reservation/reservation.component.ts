import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-reservation',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule
  ],
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent {
  reservations = [
    { id: 1, roomName: 'Conference Room A', date: new Date(), time: '10:00 AM', duration: 2 },
    { id: 2, roomName: 'Meeting Room B', date: new Date(), time: '2:00 PM', duration: 1 }
  ];

  cancelReservation(id: number) {
    // Placeholder: implement cancellation logic later
    this.reservations = this.reservations.filter(r => r.id !== id);
  }
}
