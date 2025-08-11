export interface User {
  id: number;          // Long : number
  username: string;
  email: string;
  password: string;
  role: string;        // Could be 'user' or 'admin'
}
