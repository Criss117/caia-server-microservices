"use client";
import { useEffect } from "react";
import { ChevronDown, SendHorizonal } from "lucide-react";

import useCurrentConferenceState from "@/core/conferences/application/state/current-conference.state";
import { Button } from "@/core/shared/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/core/shared/components/ui/dropdown-menu";
import { Textarea } from "@/core/shared/components/ui/textarea";
import useSendInvitation from "../../application/hooks/use.send-invitation";

interface Props {
  email: string;
}

const SendInvitationButton = ({ email }: Props) => {
  const { conference } = useCurrentConferenceState();
  const { invitation, setConferenceId, setEmail, setMessage, sendInvitation } =
    useSendInvitation();

  useEffect(() => {
    if (conference?.id && email) {
      setConferenceId(conference.id);
      setEmail(email);
    }
  }, [conference?.id, email]);

  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <Button className="bg-lightprimary-200 gap-x-2">
          <p>Invitar</p>
          <ChevronDown className="w-5 h-5" />
        </Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent className="w-96">
        <DropdownMenuLabel>Invitar a un usuario</DropdownMenuLabel>
        <DropdownMenuSeparator />
        <Textarea
          placeholder="Escribe un mensaje"
          className="resize-none"
          rows={5}
          value={invitation.message}
          onChange={(e) => setMessage(e.target.value)}
        />
        <DropdownMenuItem>
          <Button className="w-full justify-between" onClick={sendInvitation}>
            <p>Enviar invitación</p>
            <SendHorizonal className="w-5 h-5" />
          </Button>
        </DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  );
};

export default SendInvitationButton;
