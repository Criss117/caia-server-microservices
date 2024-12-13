import responseInvitationAction from "../../data/actions/response-invitation";
import { useRouter } from "next/navigation";

const useResponseInvitation = (jwt: string) => {
  const router = useRouter();

  const acceptInvitation = (token: string) => {
    responseInvitationAction(jwt, token, "ACCEPTED").then((res) => {
      console.log({ res });
      if (res.status === 200) {
        alert("Invitacion aceptada correctamente");
        router.refresh();
      } else {
        alert("Error al aceptar la invitación");
      }
    });
  };

  const rejectInvitation = (token: string) => {
    responseInvitationAction(jwt, token, "REJECTED").then((res) => {
      console.log({ res });
      if (res.status === 200) {
        alert("Invitacion rechazada correctamente");
        router.refresh();
      } else {
        alert("Error al rechazar la invitación");
      }
    });
  };

  return { acceptInvitation, rejectInvitation };
};

export default useResponseInvitation;
