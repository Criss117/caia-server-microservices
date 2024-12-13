import { z } from "zod";

export const SignUpSchema = z.object({
  firstName: z.string().min(1, { message: "El nombre es obligatorio" }), // Validación para campo no vacío
  lastName: z.string().min(1, { message: "El apellido es obligatorio" }), // Validación para campo no vacío
  email: z.string().email({ message: "Debe ser un email válido" }), // Validación para formato de email
  affiliation: z.string().optional(), // Campo opcional
  password: z
    .string()
    .min(8, { message: "La contraseña debe tener al menos 8 caracteres" }), // Validación para longitud mínima
});

export const LoginSchema = z.object({
  email: z.string().email({ message: "Debe ser un email válido" }), // Validación para formato de email
  password: z
    .string()
    .min(8, { message: "La contraseña debe tener al menos 8 caracteres" }), // Validación para longitud mínima
});
