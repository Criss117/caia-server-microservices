import { z } from "zod";

export const SendPaperSchema = z.object({
  title: z.string().min(5, { message: "El titulo es obligatorio" }),
  description: z.string().min(1, { message: "La descripción es obligatoria" }),
  keys: z.string(),
  conferenceSlug: z
    .string()
    .min(1, { message: "La conferencia es obligatoria" }),
});
