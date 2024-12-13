"use server";

import { httpClient } from "@/core/shared/lib/config/http.config";
import { CommonResponse } from "@/core/shared/models/type";
import { AxiosError } from "axios";
import { LoginDto } from "../../application/models/types";

interface LoginResponse {
  email: string;
  message: string;
  jwt: string;
  status: boolean;
}

export async function loginAction(
  loginDto: LoginDto
): Promise<CommonResponse<LoginResponse | null>> {
  try {
    const response = await httpClient.post<CommonResponse<LoginResponse>>(
      "/users/login",
      {
        email: loginDto.email,
        password: loginDto.password,
      }
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
      message: "Error al iniciar sesion",
      error: "Error al iniciar sesion",
    };
  }
}
