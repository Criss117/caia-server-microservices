import { useState } from "react";
import { SendInvitationDto } from "../models/send-invitation.dto";
import { useSession } from "next-auth/react";
import sendInvitationAction from "../../data/actions/send-invitation.action";
import { useRouter } from "next/navigation";

const defaultValues: SendInvitationDto = {
  email: "",
  message: "",
  conferenceId: -1,
};

const useSendInvitation = () => {
  const router = useRouter();
  const { data: session } = useSession();

  const [invitation, setInvitation] =
    useState<SendInvitationDto>(defaultValues);

  const setMessage = (message: string) => {
    setInvitation((prev) => ({ ...prev, message }));
  };

  const setEmail = (email: string) => {
    setInvitation((prev) => ({ ...prev, email }));
  };

  const setConferenceId = (conferenceId: number) => {
    setInvitation((prev) => ({ ...prev, conferenceId }));
  };

  const sendInvitation = () => {
    if (session) {
      sendInvitationAction(invitation, session.jwt).then((res) => {
        console.log({ res });
        if (res.status === 200) {
          setInvitation(defaultValues);
          alert("Invitacion enviada correctamente");
          router.refresh();
        } else {
          alert("Error al enviar la invitación");
        }
      });
    }
  };

  return {
    invitation,
    setMessage,
    setEmail,
    setConferenceId,
    sendInvitation,
  };
};

export default useSendInvitation;
