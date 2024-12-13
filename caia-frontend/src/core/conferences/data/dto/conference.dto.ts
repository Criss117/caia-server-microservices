export interface ConferencesDto {
  id: number;
  name: string;
  description: string;
  bannerImageUrl: null;
  slug: string;
  auditMetadata: AuditMetadata;
}

export interface AuditMetadata {
  createdAt: Date;
  updatedAt: Date | null;
  deletedAt: Date | null;
}

export interface ConferenceWithRole {
  conference: ConferencesDto;
  isOrganizer: boolean;
}
