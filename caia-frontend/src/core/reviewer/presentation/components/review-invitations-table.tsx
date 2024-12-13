import { auth } from "@/auth";
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/core/shared/components/ui/table";
import { format } from "@formkit/tempo";
import findOwnInvitationsAction from "../../data/actions/find-own-invitations.action";
import InvitationActions from "./invitation-actions";
import { INVITATION_STATE } from "../../../shared/lib/constants/invitation-state";

const ReviewInvitationsTable = async () => {
  const session = await auth();
  const { data: invitations } = await findOwnInvitationsAction(
    session?.jwt || ""
  );

  return (
    <Table className="w-full xl:w-[80%] mx-auto">
      <TableCaption>Invitaciones</TableCaption>
      <TableHeader>
        <TableRow>
          <TableHead className="w-[200px]">Conferencia</TableHead>
          <TableHead>Mensaje</TableHead>
          <TableHead>Estado</TableHead>
          <TableHead>Última actualización</TableHead>
          <TableHead className="text-right">Acciones</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        {invitations.map((invitation) => (
          <TableRow key={invitation.id.conferenceId + invitation.id.userId}>
            <TableCell className="font-medium">Conferencia nueva</TableCell>
            <TableCell>{invitation.message}</TableCell>
            <TableCell>{INVITATION_STATE[invitation.state]}</TableCell>
            <TableCell>
              {format(
                new Date(
                  invitation.auditMetadata.updatedAt ||
                    invitation.auditMetadata.createdAt
                )
              )}
            </TableCell>
            <TableCell className="text-right space-x-5 justify-between">
              <InvitationActions
                disabled={invitation.state !== "PENDING"}
                token={invitation.token}
                jwt={session?.jwt || ""}
              />
            </TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  );
};

export default ReviewInvitationsTable;
