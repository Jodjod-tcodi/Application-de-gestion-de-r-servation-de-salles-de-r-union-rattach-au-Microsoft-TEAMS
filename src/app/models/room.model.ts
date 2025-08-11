export interface Room {
  id: number;
  name: string;
  capacity: number;
  location: string;
  admin_id: number;  // ID of the user who manages this room               //link to Admin.id
}
