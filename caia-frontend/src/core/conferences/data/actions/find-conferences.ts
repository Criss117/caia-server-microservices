"use server";

import { AxiosError } from "axios";

import { httpClient } from "@/core/shared/lib/config/http.config";
import { CommonResponse } from "@/core/shared/models/type";
import { ConferencesDto } from "../dto/conference.dto";

async function findWithoutAuth() {
  return await httpClient.get<CommonResponse<ConferencesDto[]>>(
    "/conferences/public"
  );
}

async function findMany(jwt: string | null) {
  return await httpClient.get<CommonResponse<ConferencesDto[]>>(
    "/conferences/find",
    {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    }
  );
}

async function findConferencesAction(
  jwt: string | null
): Promise<CommonResponse<ConferencesDto[] | null>> {
  try {
    if (!jwt) {
      const res = await findWithoutAuth();

      return {
        data: res.data.data,
        status: res.status,
        message: res.data.message,
        error: res.data.error,
      };
    }

    const res = await findMany(jwt);

    return {
      data: res.data.data,
      status: res.status,
      message: res.data.message,
      error: res.data.error,
    };
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
      message: "Error al registrar el usuario",
      error: "Error al registrar el usuario",
    };
  }
}

export default findConferencesAction;
