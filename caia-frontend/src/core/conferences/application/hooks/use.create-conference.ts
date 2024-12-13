import { httpClient } from "@/core/shared/lib/config/http.config";
import { ROUTES } from "@/core/shared/lib/constants/routes";
import { CommonResponse } from "@/core/shared/models/type";
import { useSession } from "next-auth/react";
import { useRouter } from "next/navigation";
import { useState } from "react";

interface createConferenceDto {
  name: string;
  description: string;
}

const initialData: createConferenceDto = {
  name: "",
  description: "",
};

const useCreateConference = () => {
  const router = useRouter();
  const { data: session } = useSession();
  const [data, setData] = useState<createConferenceDto>(initialData);

  const setNombre = (name: string) => {
    setData((prev) => ({ ...prev, name }));
  };

  const setDescripcion = (description: string) => {
    setData((prev) => ({ ...prev, description }));
  };

  const onSubmit = () => {
    httpClient
      .post<CommonResponse>("/conferences/create", data, {
        headers: {
          Authorization: `Bearer ${session?.jwt}`,
        },
      })
      .then((res) => {
        console.log({ res });
        if (res.status === 200) {
          router.push(ROUTES.DASHBOARD.OWN);
          return;
        }
        alert("Hubo un error al crear la conferencia");
      });
  };

  return {
    data,
    setNombre,
    setDescripcion,
    onSubmit,
  };
};

export default useCreateConference;
