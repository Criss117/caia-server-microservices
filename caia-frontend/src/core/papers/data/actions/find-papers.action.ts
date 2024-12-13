"use server";

import { CommonResponse } from "@/core/shared/models/type";
import { PaperDto } from "../dto/papers.dto";
import { httpClient } from "@/core/shared/lib/config/http.config";
import { AxiosError } from "axios";

async function findPapersAction(
  jwt: string
): Promise<CommonResponse<PaperDto[] | []>> {
  try {
    const res = await httpClient.get<CommonResponse<PaperDto[]>>(
      "/papers/own-papers",
      {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      }
    );

    return {
      data: res.data.data,
      status: res.status,
      message: res.data.message,
      error: res.data.error,
    };
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
      message: "Error al registrar el usuario",
      error: "Error al registrar el usuario",
    };
  }
}

export default findPapersAction;
