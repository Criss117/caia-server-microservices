import { ConferencesDto } from "@/core/conferences/data/dto/conference.dto";
import { PAPER_STATE } from "@/core/shared/lib/constants/paper-state";

export interface PaperDto {
  id: number;
  title: string;
  description: string;
  fileName: string;
  keys: string;
  state: keyof typeof PAPER_STATE;
  auditMetadata: AuditMetadata;
  conferenceEntity: ConferencesDto;
}

export interface AuditMetadata {
  createdAt: Date;
  updatedAt: Date;
  deletedAt: Date | null;
}
