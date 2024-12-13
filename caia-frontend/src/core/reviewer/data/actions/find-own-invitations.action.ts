"use server";

import { CommonResponse } from "@/core/shared/models/type";
import { Invitation } from "../models/invitation";
import { AxiosError } from "axios";
import { httpClient } from "@/core/shared/lib/config/http.config";

async function findOwnInvitationsAction(
  jwt: string
): Promise<CommonResponse<Invitation[]>> {
  try {
    const { data } = await httpClient.get<CommonResponse<Invitation[]>>(
      "/reviewers/invitations/find-own",
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
        data: [],
        status: error.response?.status || 500,
        message: error.response?.data.message,
        error: error.response?.data.error,
      };
    }

    return {
      data: [],
      status: 500,
      message: "Error al obtener las invitaciones",
      error: null,
    };
  }
}

export default findOwnInvitationsAction;
