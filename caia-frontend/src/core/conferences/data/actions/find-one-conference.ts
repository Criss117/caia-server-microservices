"use server";

import { httpClient } from "@/core/shared/lib/config/http.config";
import { CommonResponse } from "@/core/shared/models/type";
import { ConferencesDto, ConferenceWithRole } from "../dto/conference.dto";
import { AxiosError } from "axios";

interface Args {
  slug: string;
  jwt?: string;
}

async function findWithoutAuth(slug: string) {
  return await httpClient.get<CommonResponse<ConferencesDto>>(
    `/conferences/public/${slug}`
  );
}

async function findOne(jwt: string, slug: string) {
  return await httpClient.get<CommonResponse<ConferenceWithRole>>(
    `/conferences/private/${slug}`,
    {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    }
  );
}

async function findOneConferenceAction({
  jwt,
  slug,
}: Args): Promise<CommonResponse<ConferenceWithRole | null>> {
  try {
    if (!jwt) {
      const res = await findWithoutAuth(slug);

      return {
        data: {
          conference: res.data.data,
          isOrganizer: false,
        },
        status: res.status,
        message: res.data.message,
        error: res.data.error,
      };
    }

    const res = await findOne(jwt, slug);

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

export default findOneConferenceAction;
