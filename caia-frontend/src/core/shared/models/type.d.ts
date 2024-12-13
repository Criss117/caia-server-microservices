export interface CommonResponse<T = null> {
  data: T;
  error: string | null;
  status: number;
  message: string;
}
