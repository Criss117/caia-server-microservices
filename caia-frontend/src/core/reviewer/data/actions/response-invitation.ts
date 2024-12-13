"use server";

import { AxiosError } from "axios";
import { InvitationState } from "../models/invitation";
import { httpClient } from "@/core/shared/lib/config/http.config";
import { CommonResponse } from "@/core/shared/models/type";

async function responseInvitationAction(
  jwt: string,
  token: string,
  state: InvitationState
) {
  try {
    const { data } = await httpClient.post<CommonResponse>(
      "/reviewers/invitations/response/" + token,
      {
        state,
      },
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
      message: "Error al responder la invitación",
      error: null,
    };
  }
}

export default responseInvitationAction;
