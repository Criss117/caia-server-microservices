"use server";

import { CommonResponse } from "@/core/shared/models/type";
import { SendInvitationDto } from "../../application/models/send-invitation.dto";
import { httpClient } from "@/core/shared/lib/config/http.config";
import { AxiosError } from "axios";

async function sendInvitationAction(
  sendInvitationDto: SendInvitationDto,
  jwt: string
): Promise<CommonResponse> {
  try {
    const { data } = await httpClient.post<CommonResponse>(
      "/reviewers/invitations/send",
      sendInvitationDto,
      {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      }
    );

    return data;
  } catch (error) {
    if (error instanceof AxiosError) {
      return {
        data: null,
        status: error.response?.status || 500,
        message: error.response?.data.message,
        error: error.response?.data.error,
      };
    }

    return {
      data: null,
      status: 500,
      message: "Error al enviar la invitación",
      error: "Error al enviar la invitación",
    };
  }
}

export default sendInvitationAction;
