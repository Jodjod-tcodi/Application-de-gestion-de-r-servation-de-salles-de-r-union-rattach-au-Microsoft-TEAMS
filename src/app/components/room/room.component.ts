import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-room',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule
  ],
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent {
  rooms = [
    { id: 1, name: 'Conference Room A', capacity: 10, location: 'First Floor' },
    { id: 2, name: 'Meeting Room B', capacity: 6, location: 'Second Floor' },
    { id: 3, name: 'Executive Room', capacity: 4, location: 'Third Floor' }
  ];
}
