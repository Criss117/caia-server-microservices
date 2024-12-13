"use client";

import { Button } from "@/core/shared/components/ui/button";
import useResponseInvitation from "../../application/hooks/use.response-invitation";

interface Props {
  disabled?: boolean;
  token: string;
  jwt: string;
}

const InvitationActions = ({ token, jwt, disabled = false }: Props) => {
  const { acceptInvitation, rejectInvitation } = useResponseInvitation(jwt);
  return (
    <>
      <Button
        disabled={disabled || token === ""}
        onClick={() => acceptInvitation(token)}
      >
        Aceptar
      </Button>
      <Button
        variant="destructive"
        disabled={disabled || token === ""}
        onClick={() => rejectInvitation(token)}
      >
        Rechazar
      </Button>
    </>
  );
};

export default InvitationActions;
