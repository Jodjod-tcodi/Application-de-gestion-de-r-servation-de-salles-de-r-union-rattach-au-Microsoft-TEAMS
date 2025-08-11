export interface Reservation {
  id: number;
  date: string;
  startTime: string;
  endTime: string;
  status: string;     // 'confirmed', 'cancelled'
  user_id: number;    // link to User.id
  room_id: number;    // link to MeetingRoom.id
}
