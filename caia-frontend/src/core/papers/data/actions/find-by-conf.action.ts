"use server";

import { httpClient } from "@/core/shared/lib/config/http.config";
import { CommonResponse } from "@/core/shared/models/type";
import { PaperDto } from "../dto/papers.dto";
import { AxiosError } from "axios";

async function findPapersByConfAction(
  jwt: string,
  slug: string
): Promise<CommonResponse<PaperDto[]>> {
  try {
    const { data } = await httpClient.get<CommonResponse<PaperDto[]>>(
      `/papers/find-by-conference/${slug}`,
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
      message: "Error en el servidor",
      error: "Error en el servidor",
    };
  }
}

export default findPapersByConfAction;
