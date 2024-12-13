import { z } from "zod";
import { LoginSchema, SignUpSchema } from "./schemas";

export type SignUpDto = z.infer<typeof SignUpSchema>;

export type LoginDto = z.infer<typeof LoginSchema>;
