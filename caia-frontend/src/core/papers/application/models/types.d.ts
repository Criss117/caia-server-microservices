import { z } from "zod";
import { SendPaperSchema } from "./schemas";

export type SendPaperDto = z.infer<typeof SendPaperSchema>;
