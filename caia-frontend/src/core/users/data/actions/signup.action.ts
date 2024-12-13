"use server";

import { httpClient } from "@/core/shared/lib/config/http.config";
import { CommonResponse } from "@/core/shared/models/type";
import { AxiosError } from "axios";

interface data {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  affiliation?: string | undefined;
}

export async function signUp(data: data): Promise<CommonResponse> {
  try {
    const response = await httpClient.post<CommonResponse>(
      "/users/signup",
      data
    );

    return {
      data: response.data.data,
      status: response.status,
      message: response.data.message,
      error: response.data.error,
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
