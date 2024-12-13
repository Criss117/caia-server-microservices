export interface Invitation {
  id: ID;
  token: string;
  state: InvitationState;
  message: string;
  respondedAt: null;
  auditMetadata: AuditMetadata;
  conference: Conference;
}

export interface AuditMetadata {
  createdAt: Date;
  updatedAt: null;
  deletedAt: null;
}

export interface Conference {
  id: number;
  name: string;
  slug: string;
}

export interface ID {
  userId: number;
  conferenceId: number;
}

export type InvitationState = "ACCEPTED" | "REJECTED" | "PENDING";
